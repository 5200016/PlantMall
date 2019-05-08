/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysCouponDetailComponent } from 'app/entities/sys-coupon/sys-coupon-detail.component';
import { SysCoupon } from 'app/shared/model/sys-coupon.model';

describe('Component Tests', () => {
    describe('SysCoupon Management Detail Component', () => {
        let comp: SysCouponDetailComponent;
        let fixture: ComponentFixture<SysCouponDetailComponent>;
        const route = ({ data: of({ sysCoupon: new SysCoupon(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysCouponDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysCouponDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysCouponDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysCoupon).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
