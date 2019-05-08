/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponProductComponent } from 'app/entities/sys-coupon-product/sys-coupon-product.component';
import { SysCouponProductService } from 'app/entities/sys-coupon-product/sys-coupon-product.service';
import { SysCouponProduct } from 'app/shared/model/sys-coupon-product.model';

describe('Component Tests', () => {
    describe('SysCouponProduct Management Component', () => {
        let comp: SysCouponProductComponent;
        let fixture: ComponentFixture<SysCouponProductComponent>;
        let service: SysCouponProductService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponProductComponent],
                providers: []
            })
                .overrideTemplate(SysCouponProductComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(SysCouponProductComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(SysCouponProductService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new SysCouponProduct(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.sysCouponProducts[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
