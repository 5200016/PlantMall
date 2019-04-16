/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { PlantMallTestModule } from '../../../test.module';
import { SysBannerDetailComponent } from 'app/entities/sys-banner/sys-banner-detail.component';
import { SysBanner } from 'app/shared/model/sys-banner.model';

describe('Component Tests', () => {
    describe('SysBanner Management Detail Component', () => {
        let comp: SysBannerDetailComponent;
        let fixture: ComponentFixture<SysBannerDetailComponent>;
        const route = ({ data: of({ sysBanner: new SysBanner(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [PlantMallTestModule],
                declarations: [SysBannerDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(SysBannerDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(SysBannerDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.sysBanner).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
