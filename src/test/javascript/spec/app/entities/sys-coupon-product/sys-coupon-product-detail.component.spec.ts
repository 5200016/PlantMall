/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponProductDetailComponent } from 'app/entities/sys-coupon-product/sys-coupon-product-detail.component';
import { SysCouponProduct } from 'app/shared/model/sys-coupon-product.model';

describe('Component Tests', () => {
    describe('SysCouponProduct Management Detail Component', () => {
        let comp: SysCouponProductDetailComponent;
        let fixture: ComponentFixture<SysCouponProductDetailComponent>;
        const route = ({ data: of({ sysCouponProduct: new SysCouponProduct(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponProductDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysCouponProductDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponProductDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysCouponProduct).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
