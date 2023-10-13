import { Component,Input } from '@angular/core';

@Component({
  selector: 'app-mercadona-product-detail',
  templateUrl: './mercadona-product-detail.component.html',
  styleUrls: ['./mercadona-product-detail.component.css']
})
export class MercadonaProductDetailComponent {

  @Input() product

}
